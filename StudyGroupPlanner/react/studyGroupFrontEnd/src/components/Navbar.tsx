import { AppBar, Button, TextField, Toolbar, Typography } from "@mui/material";
import { TabNav } from "@radix-ui/themes";
import { useState } from "react";
import { useAuth } from "../hooks/AuthContext";
import LoginPage from "./Login";
import CreateUserForm from "./CreateUserForm";
import { useCurrentPage } from "../context/CurrentPageContext";
import NavbarMenu from "./ui/NavbarMenu";

interface NavbarProps {
    search: string;
    setSearch: React.Dispatch<React.SetStateAction<string>>;
}

enum CurrentPage {
    Home = "Home",
    Account = "Account",
}

export default function Navbar({search, setSearch}: NavbarProps) {
    const { user, logoutUser } = useAuth();
    const [loginOpen, setLoginOpen] = useState(false);
    const { activePage, changeActivePage } = useCurrentPage();

    return (
        <>
            <AppBar className = "bg-emerald-600" position = "static">
                <Toolbar className = "flex justify-evenly">
                    <Button color = "inherit" onClick={() => changeActivePage(CurrentPage.Home)}>Home</Button>

                    {user ? (
                        <>
                            <Button color = "inherit" onClick = {logoutUser}>
                                Logout
                            </Button> 
                        </>
                    ) : (
                        <Button color = "inherit" onClick = {() => setLoginOpen(true)}>
                            Login
                        </Button>
                    )}
                    
                    {/* <Button color = "inherit">Sessions</Button> */}
                    <NavbarMenu></NavbarMenu>

                    <Button color = "inherit" onClick={() => changeActivePage(CurrentPage.Account)}>Account</Button>

                    {/* Make the search function live inside the parent app */}
                    <TextField 
                        value = {search}
                        onChange = {(e) => setSearch(e.target.value)}
                        placeholder = "Search..." 
                        variant = "outlined" size = "small" sx={{
                        backgroundColor: "white",
                        borderRadius: 1,
                        width: 250,
                        mr: 2,
                    }}/>
                </Toolbar>
            </AppBar>

            <LoginPage
                open = {loginOpen}
                onClose = {() => setLoginOpen(false)}/>

        </>
    );
}
