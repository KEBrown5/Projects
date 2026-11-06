import { SessionStatusEnum, type CreateStudySessionRequest } from "../domain/domain";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { createStudySession } from "../lib/api";
import { Button, Dialog, DialogActions, DialogContent, DialogTitle } from "@mui/material";
import { DateTimePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { useStudySession } from "../context/StudySessionContext";

interface createStudySessionDialogProps {
    open: boolean;
    onClose: () => void;
}

export default function CreateStudySessionDialogue({open, onClose}: createStudySessionDialogProps) {
    const {register, control, watch, handleSubmit, formState: { errors }} = useForm<CreateStudySessionRequest>({defaultValues: {name: "", session_start: null, session_end: null, location: ""}});
    // Watch the session start date for use in validating future dates
    const sessionStart = watch("session_start");
    const {createSession} = useStudySession();

    const onSubmit = async (data) => {
        console.log(data);

        createSession(data);

        onClose();
    }

    return (
        <Dialog open = {open} onClose = {onClose} fullWidth maxWidth = "sm">
            <DialogTitle>
                Create Study Session
            </DialogTitle>
            
            <form onSubmit = {handleSubmit(onSubmit)}>
                <DialogContent>
                    <div className = "flex flex-col gap-5">
                        <input type = "text" placeholder = "Name" {...register('name', { required: "Name is required"})}></input>

                        <input type = "text" placeholder = "Description"{...register('description')}></input>

                        {/* Localization provider is needed for dates and times */}
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            {/* Controller hooks up the date inputs into the hook form */}
                            <Controller
                                name="session_start"
                                control={control}
                                rules={{
                                    required: "Please select a date",
                                    validate: (value) => 
                                        !value || value > new Date() ? true : "Start time must be in the future",
                                }}
                                render={({ field, fieldState }) => (
                                    <DateTimePicker
                                        label="Session Start"
                                        value={field.value}
                                        onChange={field.onChange}
                                        slotProps={{
                                            textField: {
                                                error: !!fieldState.error,
                                                helperText: fieldState.error?.message,
                                            },
                                        }}
                                    />
                                )}
                            />

                            <Controller
                                name="session_end"
                                control={control}
                                rules={{
                                    validate: (value) => {
                                        if (!value || !sessionStart) {
                                            return true;
                                        }

                                        return value > sessionStart || "End time must be after start time";
                                    }
                                }}
                                render={({ field, fieldState }) => (
                                    <DateTimePicker
                                        label="Session End"
                                        value={field.value}
                                        onChange={field.onChange}
                                        minDateTime={sessionStart ?? undefined}
                                        slotProps={{
                                            textField: {
                                                error: !!fieldState.error,
                                                helperText: fieldState.error?.message,
                                            },
                                        }}
                                    />
                                )}
                            />
                        </LocalizationProvider>

                        <input type = "text" placeholder = "Location" {...register('location', { required: "Location is required"})}></input>
                    </div>
                </DialogContent>

                <DialogActions>
                    <Button onClick = {onClose}>Cancel</Button>
                    <Button variant = "contained" type = "submit">Create Session</Button>
                </DialogActions>
            </form>       
        </Dialog>
    )
}
