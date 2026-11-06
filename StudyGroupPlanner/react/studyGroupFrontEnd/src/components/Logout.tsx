import { useAuth } from "../hooks/AuthContext";


export default function LogoutPage() {
    const { logoutUser, user, loading } = useAuth();

    if (loading) {
        return <p>Loading...</p>
    }

    if (user) {
        return (
            <button onSubmit = {logoutUser}>Logout</button>
        )
    }

    return (
        <h1>No User</h1>
    )
}