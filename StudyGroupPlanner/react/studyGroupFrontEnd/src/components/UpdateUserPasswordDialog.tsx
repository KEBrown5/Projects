import { Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from "@mui/material";
import { useAuth } from "../hooks/AuthContext";
import { useForm } from "react-hook-form";
import type { UpdateUserPasswordRequest } from "../domain/domain";
import { updateUserPassword } from "../lib/api";
import { useState } from "react";

interface updateUserPasswordDialogProps {
    open: boolean;
    onClose: () => void;
}

export default function UpdateUserPasswordDialog({open, onClose}: updateUserPasswordDialogProps) {
    const {user} = useAuth();

    if (user == null) {
        return;
    }

    // On change sets the form to update with every stroke, allowing for password tracking
    const {register, watch, handleSubmit, formState: { errors }} = useForm<UpdateUserPasswordRequest>({mode: "onChange"});

    // Watch keeps track of the password value
    const passwordValue = watch("password");

    const onSubmit = async (data: UpdateUserPasswordRequest) => {
        console.log("Update User Password Called");

        try {
            const updateUserPasswordRequest: UpdateUserPasswordRequest = {
                id: user.id,
                password: data.password,
                confirmPassword: data.confirmPassword,
            };

            updateUserPassword(updateUserPasswordRequest);
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
                            label="New Password"
                            type="password"
                            fullWidth
                            {...register("password", {
                                required: "Password is required"
                            })}
                            error={!!errors.password}
                            helperText={errors.password?.message}
                        />

                        <TextField
                            label="Confirm Password"
                            type="password"
                            fullWidth
                            {...register("confirmPassword", {
                                required: "Confirm Password",
                                validate: (value) => value === passwordValue || "Passwords do not match"                    
                            })}
                            error={!!errors.password}
                            helperText = {errors.confirmPassword?.message}
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