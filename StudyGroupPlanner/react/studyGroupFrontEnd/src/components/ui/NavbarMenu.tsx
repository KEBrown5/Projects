import { Button, Menu, MenuItem } from "@mui/material";
import React, { useId, useState, type MouseEvent } from "react";


export default function NavbarMenu() {
    const id = useId();
    const menuId = `${id}-menu`
    const buttonId = `${id}-button`
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);

    const handleClick = (event: MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    }

    const handleClose = () => {
        setAnchorEl(null);
    }

    return (
        <div>
            <Button 
                color = "inherit"
                id = {buttonId} 
                aria-controls = {open ? menuId : undefined} 
                aria-haspopup = "true" 
                aria-expanded = {open} 
                onClick = {handleClick}
            >
                Sessions
            </Button>

            <Menu id = {menuId} anchorEl = {anchorEl} open = {open} onClose = {handleClose} slotProps = {{list: {'aria-labelledby': buttonId,}}}>
                <MenuItem onClick = {handleClose}>Create Study Session</MenuItem>
                <MenuItem onClick = {handleClose}>View Your Sessions</MenuItem>
                <MenuItem onClick = {handleClose}>View All Sessions</MenuItem>
                <MenuItem onClick = {handleClose}>View Registered Sessions</MenuItem>
            </Menu>
        </div>
    );
}