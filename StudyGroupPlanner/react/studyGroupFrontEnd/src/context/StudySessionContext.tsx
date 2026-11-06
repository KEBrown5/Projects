import { createContext, useContext, useState, type ReactNode } from "react";
import { SessionStatusEnum, type CreateStudySessionRequest, type DeleteStudySessionRequest, type ListStudySessionResponse, type RegisterUserForStudySessionRequest, type StudySession, type UpdateStudySessionRequest, type User } from "../domain/domain";
import { createStudySession, deleteStudySession, getRegisteredSessionsForUser, listStudySessions, listStudySessionsForUser, registerForSession, updateStudySession } from "../lib/api";

interface StudySessionProviderProps {
    children: ReactNode;
}

interface StudySessionContext {
    sessions: StudySession[];
    userSessions: StudySession[];
    registeredSessions: StudySession[];
    createSession: (request: CreateStudySessionRequest) => void;
    deleteSession: (request: DeleteStudySessionRequest) => void;
    updateSession: (request: UpdateStudySessionRequest) => void;
    getStudySessions: () => void;
    getUserStudySessions: () => void;
    getRegisteredStudySessions: () => void;
}

export const StudySessionContext = createContext<StudySessionContext | null>(null);

// Not in use anymore - Using TanStack instead
export function StudySessionProvider({children}:StudySessionProviderProps) {
    const [sessions, setSessions] = useState<StudySession[]>([]);
    const [userSessions, setUserSessions] = useState<StudySession[]>([]);
    const [registeredSessions, setRegisteredSessions] = useState<StudySession[]>([]);

    const createSession = async (request: CreateStudySessionRequest) => {
        try {
            const createStudySessionRequest: CreateStudySessionRequest = {
                name: request.name,
                description: request.description,
                session_start: request.session_start,
                session_end: request.session_end,
                location: request.location,
                status: SessionStatusEnum.UPCOMING,
            };

            await createStudySession(createStudySessionRequest);  
            await getStudySessions();
        } catch (error) {
            console.error(error);
        }
    }

    const deleteSession = async (request: DeleteStudySessionRequest) => {
        try {
            const deleteStudySessionRequest: DeleteStudySessionRequest = {
                id: request.id,
            };

            await deleteStudySession(deleteStudySessionRequest);
            await getStudySessions();
        } catch (error) {
            console.error(error);
        }
    }

    const updateSession = async (request: UpdateStudySessionRequest) => {
        try {
            const updateStudySessionRequest: UpdateStudySessionRequest = {
                sessionId: request.sessionId,
                name: request.name,
                description: request.description,
                session_start: request.session_start,
                session_end: request.session_end,
                location: request.location,
                status: SessionStatusEnum.UPCOMING,
            }

            await updateStudySession(updateStudySessionRequest);
            await getStudySessions();
        } catch (error) {
            console.error(error);
        } 
    }

    // All study sessions
    const getStudySessions = async () => {
        try {
            const response = await listStudySessions(0);

            setSessions(response.content);
        } catch (error) {
            console.error(error);
        }   
    }

    // Study sessions created by the user
    const getUserStudySessions = async () => {
        try {
            const response = await listStudySessionsForUser(0);
            setUserSessions(response.content);
        } catch (error) {
            console.error(error);
        }
    }

    const registerUserForSession = async (request: RegisterUserForStudySessionRequest) => {
        try {
            const response = await registerForSession(request);
        } catch (error) {
            console.error(error);
        }
    }

    const getRegisteredStudySessions = async () => {
        try {
            const response = await getRegisteredSessionsForUser(0);
            setRegisteredSessions(response.content);
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <StudySessionContext.Provider value = {{sessions, userSessions, registeredSessions, createSession, deleteSession, updateSession, getStudySessions, getUserStudySessions, getRegisteredStudySessions}}>{children}</StudySessionContext.Provider>
    )
}

export function useStudySession() {
    const context = useContext(StudySessionContext);

    if (!context) {
        throw new Error("useCurrentPage must be used within CurrentPageProvider");
    }

    return context;
}