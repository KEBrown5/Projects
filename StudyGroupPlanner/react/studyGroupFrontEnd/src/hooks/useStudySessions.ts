import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { 
  listStudySessions, 
  listStudySessionsForUser, 
  getRegisteredSessionsForUser,
  createStudySession,
  deleteStudySession,
  updateStudySession 
} from "../lib/api";
import type { 
  CreateStudySessionRequest, 
  DeleteStudySessionRequest, 
  UpdateStudySessionRequest 
} from "../domain/domain";

// Key factory for cache management
export const sessionKeys = {
  all: ["sessions"] as const,
  list: (type: string, page: number) => [...sessionKeys.all, type, page] as const,
};

// Fetcher Hook (Fetches ONLY the active tab)
export function useStudySessions(activeTab: "All" | "User" | "Registered", page: number = 0, enabled: boolean = true) {
  return useQuery({
    queryKey: sessionKeys.list(activeTab, page),
    queryFn: () => {
      if (activeTab === "User") return listStudySessionsForUser(page);
      if (activeTab === "Registered") return getRegisteredSessionsForUser(page);
      return listStudySessions(page);
    },
    enabled, // Prevents query execution if user isn't logged in for User/Registered tabs
  });
}

// Mutation Hooks (Automatically invalidates cache after changes)
export function useSessionMutations() {
  const queryClient = useQueryClient();

  // Refetch all session queries whenever any mutation succeeds
  const invalidateSessions = () => {
    queryClient.invalidateQueries({ queryKey: sessionKeys.all });
  };

  const createMutation = useMutation({
    mutationFn: (data: CreateStudySessionRequest) => createStudySession(data),
    onSuccess: invalidateSessions,
  });

  const updateMutation = useMutation({
    mutationFn: (data: UpdateStudySessionRequest) => updateStudySession(data),
    onSuccess: invalidateSessions,
  });

  const deleteMutation = useMutation({
    mutationFn: (data: DeleteStudySessionRequest) => deleteStudySession(data),
    onSuccess: invalidateSessions,
  });

  return { createMutation, updateMutation, deleteMutation };
}