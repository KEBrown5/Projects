import { Button, Dialog, DialogActions, DialogContent, DialogTitle } from "@mui/material";
import type { DeleteStudySessionRequest } from "../domain/domain";
import { deleteStudySession } from "../lib/api";
import { useStudySession } from "../context/StudySessionContext";

interface deleteStudySessionDialogProps {
    open: boolean;
    onClose: () => void;
    sessionId: string;
}

export default function DeleteDialog({open, onClose, sessionId}: deleteStudySessionDialogProps) {
    const {deleteSession} = useStudySession();

    const onSubmit = async () => {
        const deleteStudySessionRequest: DeleteStudySessionRequest = {
            id: sessionId,
        };

        deleteSession(deleteStudySessionRequest);
        onClose();
    }

    return (
        <Dialog open = {open} onClose = {onClose} fullWidth maxWidth = "sm">
            <DialogTitle>
                Delete Session
            </DialogTitle>
            
            <DialogActions>
                <Button onClick = {onClose} >Cancel</Button>
                <Button onClick = {onSubmit}>Delete?</Button>
            </DialogActions>
        </Dialog>
    )
}