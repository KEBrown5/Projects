import { Button, Card, CardActions, CardContent, CardHeader, Typography } from "@mui/material";
import type { User } from "../../domain/domain";
import { useAuth } from "../../hooks/AuthContext";
import DeleteDialog from "../DeleteStudySessionDialog";
import { useState } from "react";
import UpdateUserDialog from "../UpdateUserProfileDialog";
import UpdateUserPasswordDialog from "../UpdateUserPasswordDialog";

export default function UserCard() {
    const { user, loading: authLoading } = useAuth();
    const [profileOpen, setProfileOpen] = useState(false);
    const [passwordOpen, setPasswordOpen] = useState(false)

    if (user == null) {
        return;
    }

    return ( 
        <>   
            <div className = "flex pt-5 justify-center">
                <Card sx = {{maxWidth: 700}}>
                    <CardContent>
                        <div className = "p-3">
                            <Typography variant = "h6">
                                Name: {user.name}
                            </Typography>
                        </div>
                        
                        <div className = "p-3 flex justify-evenly">
                            <Typography variant = "h6">
                                ID: {user.id}
                            </Typography>
                        </div>

                        <div className = "p-3">
                            <Typography variant = "h6">
                                Email: {user.email}
                            </Typography>
                        </div>
                    </CardContent>

                    <CardActions>
                            <Button onClick = {() => setProfileOpen(true)}>Edit Profile</Button>
                            <Button onClick = {() => setPasswordOpen(true)}>Edit Password</Button>
                    </CardActions>
                </Card>
            </div>

            <UpdateUserDialog open = {profileOpen} onClose = {() => setProfileOpen(false)}/>
            <UpdateUserPasswordDialog open = {passwordOpen} onClose = {() => setPasswordOpen(false)}/>
        </>
    )


}