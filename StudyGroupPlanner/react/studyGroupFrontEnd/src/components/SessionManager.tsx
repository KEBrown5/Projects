import { useEffect, useState } from "react";
import { useStudySession } from "../context/StudySessionContext";
import CreateStudySessionDialogue from "./CreateStudySessionDialog";
import StudySessionCard from "./ui/StudySessionCard";
import { Box, Button, CircularProgress, Pagination, Typography } from "@mui/material";
import { useAuth } from "../hooks/AuthContext";
import { useStudySessions } from "../hooks/useStudySessions";

enum ActiveSessions  {
    All = "All",
    User = "User",
    Registered = "Registered",
}

export default function SessionManager() {
    const [activeTab, setActiveTab] = useState<ActiveSessions>(ActiveSessions.All);
    const [createSessionOpen, setCreateSessionOpen] = useState(false);
    const [page, setPage] = useState(0);
    const { user } = useAuth();

    // Fetch data based on active tab (Only fetches what is needed)
    const isUserTab = activeTab !== ActiveSessions.All;
    const { data, isLoading, isError } = useStudySessions(activeTab, page, !isUserTab || !!user);

    // Extract session content (Spring Boot Page object contains 'content')
    const sessionsList = data?.content ?? [];
    const totalPages = data?.totalPages ?? 1;

    // Sets pagination to 0 on default and when switching pages
    useEffect(() => {
        setPage(0);
    }, [activeTab]);

    const handlePageChange = (event: React.ChangeEvent<unknown>, value: number) => {
        setPage(value - 1) // Convert MUI's 1-index back to springboot's 0-index
    }

    return (
        <div>
            <div className = "flex justify-between">
                <Typography className = "font-arima" variant="h3">Study Sessions</Typography>

                {totalPages > 1 && (
                        <Box>
                            <Pagination
                                count={totalPages} 
                                page={page + 1} // Convert Spring's 0-index to MUI's 1-index for UI display
                                onChange={handlePageChange} 
                                color="primary" 
                                size="large"
                            />
                        </Box>
                    )}
            </div>

        <Button onClick={() => setCreateSessionOpen(true)} variant="text" sx={{ mb: 3 }}>
            Create Study Session
        </Button>

        <Button onClick={() => setActiveTab(ActiveSessions.User)} variant="text" sx={{ mb: 3 }}>
            View Your Sessions
        </Button>

        <Button onClick={() => setActiveTab(ActiveSessions.All)} variant="text" sx={{ mb: 3 }}>
            View All Sessions
        </Button>

        <Button onClick={() => setActiveTab(ActiveSessions.Registered)} variant="text" sx={{ mb: 3 }}>
            View Registered Sessions
        </Button>

        {/* Render Loading, Error, or Data cleanly */}
        {isLoading ? (
            <CircularProgress />
        ) : isError ? (
            <p>Error loading study sessions. Please try again.</p>
        ) : sessionsList.length === 0 ? (
            <p>No study sessions found.</p>
        ) : (
            <div>
            {sessionsList.map((session) => (
                <StudySessionCard key={session.id} session={session} />
            ))}
            </div>
        )}

        <CreateStudySessionDialogue
            open={createSessionOpen}
            onClose={() => setCreateSessionOpen(false)}
        />
        </div>
    );
}