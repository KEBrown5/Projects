import {
    createContext,
    useContext,
    useEffect,
    useState,
    type ReactNode,
} from "react";

import type { User, UserLoginRequest } from "../domain/domain";
import { getCurrentUser, login } from "../lib/api";

interface AuthContextType {
    user: User | null;
    loading: boolean,
    login: (request:UserLoginRequest) => Promise<void>;
    logoutUser: () => void;
    loadCurrentUser: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
    children: ReactNode;
}

export function AuthProvider({children}: AuthProviderProps) {
    const [user, setUser] = useState<User | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadCurrentUser();
    }, []);

    const loadCurrentUser = async () => {
        const token = localStorage.getItem("token");

        if (!token) {
            setLoading(false);
            return;
        }

        try {
            const response = await getCurrentUser();

            if (!response.ok) {
                localStorage.removeItem("token");
                setUser(null);
                return;
            }

            const userData = await response.json();
            setUser(userData);
        } catch (error) {  
            console.error(error);

            localStorage.removeItem("token");
            setUser(null);
        } finally {
            setLoading(false);
        }
    };
    
    const loginUser = async (request: UserLoginRequest) => {
        const responseBody = await login(request);
        
        localStorage.setItem("token", responseBody.token);

        loadCurrentUser();
    };

    const logoutUser = () => {
        localStorage.removeItem("token");
        setUser(null);
    }

    return (
        <AuthContext.Provider value = {{user, loading, login: loginUser, logoutUser, loadCurrentUser}}>
            {children}
        </AuthContext.Provider>
    )
}

export function useAuth() {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error("useAuth must be used inside AuthProvider");
    }

    return context;
}