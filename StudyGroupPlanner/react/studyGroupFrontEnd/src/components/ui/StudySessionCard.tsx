import { Button, CardHeader } from '@mui/material';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { useState } from 'react';
import { useMutation, useQueryClient } from '@tanstack/react-query';

import type { RegisterUserForStudySessionRequest, StudySession } from '../../domain/domain';
import { useAuth } from '../../hooks/AuthContext';
import { registerForSession, unregisterFromSession } from '../../lib/api'; 
import { useStudySessions, sessionKeys } from '../../hooks/useStudySessions'; // Your TanStack hooks
import DeleteDialog from '../DeleteStudySessionDialog';
import UpdateSessionDialog from '../UpdateStudySessionDialog';

interface StudySessionCardProps {
    session: StudySession;
}

export default function StudySessionCard({session} : StudySessionCardProps) {
    const [deleteSessionOpen, setDeleteSessionOpen] = useState(false);
    const [editSessionOpen, setEditSessionOpen] = useState(false);
    
    const { user } = useAuth();
    const queryClient = useQueryClient();

    // Fetch the user's registered sessions from the cache to check their status
    const { data: registeredData } = useStudySessions("Registered", 0, !!user);
    const registeredSessions = registeredData?.content ?? [];
    
    const isRegistered = registeredSessions.some((rs: StudySession) => rs.id === session.id);

    // Setup the Register Mutation
    const registerMutation = useMutation({
        mutationFn: (request: RegisterUserForStudySessionRequest) => registerForSession(request),
        onSuccess: () => {
            // Instantly refreshes all lists (All, User, Registered)
            queryClient.invalidateQueries({ queryKey: sessionKeys.all }); 
        }
    });

    // Setup the Un-Register Mutation
    const unregisterMutation = useMutation({
        mutationFn: (request: RegisterUserForStudySessionRequest) => unregisterFromSession(request),
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: sessionKeys.all });
        }
    });

    const formatTime = (date?: Date) => {
        if (!date) return "";
        return new Date(date).toLocaleTimeString("en-US", { hour: "2-digit", minute: "2-digit" }); 
    };

    const formatDate = (date?: Date) => {
        if (!date) return "";
        return new Date(date).toLocaleDateString("en-US");
    }

    const organizer = session.organizer.userId;
    
    // Combine pending states to disable buttons while either request is happening
    const isUpdating = registerMutation.isPending || unregisterMutation.isPending;

    return (
        <>
            <Card className="mb-5 rounded-lg" sx={{minWidth: 600}}>
                <CardHeader sx={{ "& .MuiTypography-root": { fontSize: "50px", fontFamily: "var(--font-andika)"}}} title={session.name} />

                <hr></hr>

                <CardContent sx={{"& .MuiTypography-root": {fontSize: "30px", fontFamily: "var(--font-mulish)"}}}> 
                    <div className = "flex justify-between">
                        <Typography>
                            Organizer:
                        </Typography>

                        <Typography>
                            {session.organizer.name}
                        </Typography>
                    </div>

                    <div className = "flex justify-between">
                        <Typography>
                            Location:
                        </Typography>

                        <Typography>
                            {session.location}
                        </Typography>
                    </div>

                    <div className = "flex justify-between">
                        <Typography>
                            Date:
                        </Typography>

                        <Typography>
                            {formatDate(session.session_start)}
                        </Typography>
                    </div>

                    <div className = "flex justify-between">
                        <Typography>
                            Start Time:
                        </Typography>

                        <Typography>
                            {formatTime(session.session_start)}
                        </Typography>
                    </div>

                    <div className = "flex justify-between">
                        <Typography>
                            End Time:
                        </Typography>

                        <Typography>
                            {formatTime(session.session_end)}
                        </Typography>
                    </div>

                    <div className = "flex justify-between">
                        <Typography>
                           Status:
                        </Typography>

                        <Typography>
                            {session.status}
                        </Typography>
                    </div>

                    <div className = "flex justify-between">
                        <Typography>
                            Description:
                        </Typography>

                        <Typography>
                            {session.description}
                        </Typography>
                    </div>
                </CardContent>

                <CardActions>
                    {user != null && user.id === organizer ? (
                        <>
                            <Button onClick={() => setEditSessionOpen(true)}>Edit</Button>
                            <Button onClick={() => setDeleteSessionOpen(true)}>Delete</Button>
                        </>
                    ) : user != null ? (
                        /* Toggle between Un-Register and Register */
                        isRegistered ? (
                            <Button 
                                onClick={() => unregisterMutation.mutate({ sessionId: session.id })} 
                                disabled={isUpdating}
                                color="error"
                            >
                                {isUpdating ? "Updating..." : "Un-Register"}
                            </Button>
                        ) : (
                            <Button 
                                onClick={() => registerMutation.mutate({ sessionId: session.id })} 
                                disabled={isUpdating}
                                color="primary"
                            >
                                {isUpdating ? "Updating..." : "Register"}
                            </Button>
                        )
                    ) : null}
                </CardActions>
            </Card>

            <DeleteDialog
                open={deleteSessionOpen} 
                onClose={() => setDeleteSessionOpen(false)}
                sessionId={session.id}
            />
            <UpdateSessionDialog 
                open={editSessionOpen} 
                onClose={() => setEditSessionOpen(false)} 
                session={session}
            />
        </>
    )
}