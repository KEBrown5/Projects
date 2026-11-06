import { useForm } from "react-hook-form";
import type { UpdateUserRequest, User } from "../domain/domain";
import { updateUser } from "../lib/api";
import { Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from "@mui/material";
import { useAuth } from "../hooks/AuthContext";
import { useEffect } from "react";

interface updateUserDialogProps {
    open: boolean;
    onClose: () => void;
}

export default function UpdateUserDialog({open, onClose}: updateUserDialogProps) {
    const { user, loadCurrentUser } = useAuth();

    if (user == null) {
        return;
    }

    const {register, handleSubmit, formState: { errors }} = useForm<UpdateUserRequest>({
        defaultValues: {
            name: user.name,
            email: user.email,
        }
    });
    
    const onSubmit = async (data: UpdateUserRequest) => {
        console.log("Update User Called");

        try {
            const updateUserRequest: UpdateUserRequest = {
                id: user.id,
                name: data.name,
                email: data.email,
            };

            await updateUser(updateUserRequest);
            await loadCurrentUser();
            onClose();
        } catch (error) {
            console.error(error);
        } 
    }

    return (
        <Dialog open = {open} onClose = {onClose} fullWidth maxWidth = "sm">
            <DialogTitle>
                Update User
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
                            label="Email"
                            fullWidth
                            {...register("email", {
                                required: "Email is required"
                            })}
                            error={!!errors.email}
                        />
                    </div>
                </DialogContent>

                <DialogActions>
                    <Button onClick = {onClose} >Cancel</Button>
                    <Button type = "submit">Update</Button>
                </DialogActions>
            </form>          
        </Dialog>
    );
}