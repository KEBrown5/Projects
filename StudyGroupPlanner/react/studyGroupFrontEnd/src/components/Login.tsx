import { useState } from "react";
import { useAuth } from "../hooks/AuthContext";
import { useForm } from "react-hook-form";
import type { UserLoginRequest } from "../domain/domain";
import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button, } from "@mui/material";
import CreateUserForm from "./CreateUserForm";

interface LoginPageProps {
    open: boolean;
    onClose: () => void;
}

export default function LoginPage({open, onClose}: LoginPageProps) {
    const { login, user, loading } = useAuth();
    const [registerOpen, setRegisterOpen] = useState(false);
    const {register, handleSubmit, formState: { errors }} = useForm<UserLoginRequest>();

    const onSubmit = async (data: UserLoginRequest) => {
        console.log(data);

        try {
            const userLoginRequest: UserLoginRequest = {
                email: data.email,
                password: data.password,
            }

            await login(userLoginRequest);
            onClose()
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <>
            <Dialog open = {open} onClose = {onClose} fullWidth maxWidth = "sm">
                <DialogTitle>
                    Login
                </DialogTitle>

                <form onSubmit = {handleSubmit(onSubmit)}>
                    <DialogContent>  
                        <div className = "flex flex-col gap-5">
                            <input type = "text" placeholder = "Email" {...register('email', { required: "Email is required"})}></input>
                            {errors.email && ( <p>{errors.email.message}</p> )}

                            <input type = "text" placeholder = "Password" {...register('password', { required: "Password is required"})}></input>
                            {errors.password && (<p>{errors.password.message}</p>)}
                        </div>
                    </DialogContent>

                    <DialogActions>
                        <Button sx={{ mr: 'auto'}} onClick = {() => {setRegisterOpen(true); onClose();}}>
                            Register
                        </Button>

                        <Button onClick = {onClose}>
                            Cancel
                        </Button>

                        <Button type = "submit" variant = "contained">
                            Login
                        </Button>
                    </DialogActions>
                </form>
            </Dialog>

            <CreateUserForm 
                    open = {registerOpen} 
                    onClose = {() => setRegisterOpen(false)}/>
        </>
    )
}