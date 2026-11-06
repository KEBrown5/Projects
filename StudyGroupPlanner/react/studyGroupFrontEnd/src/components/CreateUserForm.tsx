import { useForm } from "react-hook-form";
import type { CreateUserRequest } from "../domain/domain";
import { createUser } from "../lib/api";
import { Button, Dialog, DialogActions, DialogContent, DialogTitle } from "@mui/material";

interface createUserFormProps {
    open: boolean;
    onClose: () => void;
}

export default function CreateUserForm({open, onClose}: createUserFormProps) {
    const {register, handleSubmit, formState: { errors }} = useForm();

    const onSubmit = async (data) => {
        console.log(data);

        try {
            const createUserRequest: CreateUserRequest = {
                name: data.name,
                email: data.email, 
                password: data.password,
            };

            await createUser(createUserRequest);
            onClose();
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <Dialog open = {open} onClose = {onClose} fullWidth maxWidth = "sm">
            <DialogTitle>
                Register
            </DialogTitle>
            <form onSubmit = {handleSubmit(onSubmit)}>
                <DialogContent>
                    <div className = "flex flex-col gap-5">
                        <input type = "text" placeholder = "Name" {...register('name', { required: "Name is required"})}></input>

                        <input type = "text" placeholder = "Email" {...register('email', { required: "Email is required"})}></input>

                        <input type = "text" placeholder = "Password" {...register('password', { required: "Password is required"})}></input>
                    </div>
                </DialogContent>

                <DialogActions>
                    <Button onClick = {onClose}>Cancel</Button>

                    <Button type = "submit" variant = "contained">Register</Button>
                </DialogActions>
            </form>
        </Dialog>
    )   
}