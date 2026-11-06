import { useState } from 'react';
import StudySessionCard from './components/ui/StudySessionCard';
import LoginPage from './components/Login';
import Navbar from './components/Navbar';
import CreateUserForm from './components/CreateUserForm';
import { AuthProvider } from './hooks/AuthContext';
import { CurrentPageProvider, useCurrentPage } from './context/CurrentPageContext';
import UserAccount from './components/UserAccount';
import SessionManager from './components/SessionManager';

export default function App() {
    const [search, setSearch] = useState("");
    const {activePage} = useCurrentPage(); 

    return (
        <div className = "bg-rose-100">
            <Navbar search = {search} setSearch = {setSearch} />

            {activePage === "Home" ? (
                
                <div className = "flex justify-evenly pt-20">
                    <div className = "mx-10">
                        <SessionManager></SessionManager>
                    </div>
                </div>
                
            ) : (
                <UserAccount/>
            )}
        </div>
    )
}
