// Handles api calls to authenticated endpoints by handling token retrieval/error handling

export const apiFetch = async (
    url: string,
    options: RequestInit = {}
) => {
    const token = localStorage.getItem("token");

    if (!token) {
        throw new Error("User not authenticated");
    }

    const response = await fetch(url, {
        ...options,
        headers: {
            "Content-Type": "application/json",
            ...options.headers,
            Authorization: `Bearer ${token}`,
        },
    });

    if (!response.ok) {
        throw new Error("Error fetching at url: " + url);
    }

    return response;
};