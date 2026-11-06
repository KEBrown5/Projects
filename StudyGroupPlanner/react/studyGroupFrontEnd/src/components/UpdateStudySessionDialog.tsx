import { Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from "@mui/material";
import { SessionStatusEnum, type StudySession, type UpdateStudySessionRequest } from "../domain/domain";
import { updateStudySession } from "../lib/api";
import { useForm } from "react-hook-form";
import { useStudySession } from "../context/StudySessionContext";

interface updateStudySessionDialogProps {
    open: boolean;
    onClose: () => void;
    session: StudySession;
}

export default function UpdateSessionDialog({open, onClose, session}: updateStudySessionDialogProps) {
    const {register, handleSubmit, formState: { errors }} = useForm<UpdateStudySessionRequest>({
        defaultValues: {
            sessionId: session.id,
            name: session.name,
            description: session.description,
            location: session.location,
            session_start: session.session_start,
            session_end: session.session_end,
            status: session.status,
        }
    });

    const {updateSession} = useStudySession();
    
    const onSubmit = async (data: UpdateStudySessionRequest) => {
        console.log("Update Called");

        updateSession(data);

        onClose();
    }

    return (
        <Dialog open = {open} onClose = {onClose} fullWidth maxWidth = "sm">
            <DialogTitle>
                Update Study Session
            </DialogTitle>
            <form onSubmit = {handleSubmit(onSubmit)}>
                <DialogContent>
                    <div className = "flex flex-col gap-5">
                        <TextField
                            label="Name"
                            fullWidth
                            {...register("name", {
                                required: "Name is required"
                            })}
                            error={!!errors.name}
                        />

                        <TextField 
                            label="Description"
                            fullWidth
                            {...register("description")}
                            error={!!errors.name}
                        />

                        <TextField
                            label="Location"
                            fullWidth
                            {...register("location", {
                                required: "Location is required"
                            })}
                        />

                        <TextField
                            type="datetime-local"
                            fullWidth
                            {...register("session_start", {
                                required: "Start time is required"
                            })}
                            error={!!errors.session_start}
                        />

                        <TextField
                            type="datetime-local"
                            fullWidth
                            {...register("session_end")}
                            error={!!errors.session_end}
                        />
                    </div>
                </DialogContent>

                <DialogActions>
                    <Button onClick = {onClose}>Cancel</Button>
                    <Button type = "submit">Update</Button>
                </DialogActions>
            </form>          
        </Dialog>
    );
}