// This lists the functions that the backend provides and actually makes the calls

import type { 
    CreateStudySessionRequest,
    CreateStudySessionResponse,
    SpringBootPagination,
    ListStudySessionResponse,
    StudySession,
    CreateUserRequest,
    UserLoginRequest,
    DeleteStudySessionRequest,
    UpdateStudySessionRequest,
    UpdateUserRequest,
    UpdateUserPasswordRequest,
    RegisterUserForStudySessionRequest
    } from "../domain/domain";
import { apiFetch } from "./apiFetch";

const baseSessionUrl = "http://localhost:8080/api/v1/studySessions";
const baseUserUrl = "http://localhost:8080/api/v1/users";
const baseAuthUrl = "http://localhost:8080/api/v1/auth";

// Function for sending create study session request 
export const createStudySession = async (request: CreateStudySessionRequest): Promise<void> => {
    console.log(request);

    const response = await apiFetch(baseSessionUrl + "/createSession",
        {
            method: "POST",
            body: JSON.stringify(request),
        }
    );

    const responseBody = await response.json();

    if (!response.ok) {
        console.error(JSON.stringify(responseBody));
        throw new Error("An unknown error occured");
    }

    console.log(responseBody);
}

export const updateStudySession = async(request: UpdateStudySessionRequest) => {
    console.log(request);

    const response = await apiFetch(baseSessionUrl + "/updateSession", {
            method: "PUT",
            body: JSON.stringify(request),
        },
    );
}

// Function for listing existing study sessions
export const listStudySessions = async(page: number): Promise<SpringBootPagination<StudySession>> => {
    const response = await fetch(baseSessionUrl + `?page=${page}&size=5`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    if (!response.ok) {
        throw new Error("An unknown error occured");
    }

    const responseBody = await response.json() as SpringBootPagination<ListStudySessionResponse>;
    console.log(responseBody);
    return {
        ...responseBody,
        content: responseBody.content.map((session) => ({
            id: session.sessionId,
            name: session.name,
            description: session.description,
            session_start: session.sessionStart,
            session_end: session.sessionEnd,
            location: session.location,
            status: session.status,
            organizer: session.organizer,
            attendees: session.attendees,
        })),
    }
};

export const deleteStudySession = async (request: DeleteStudySessionRequest) => {
    const response = await apiFetch(baseSessionUrl + "/deleteSession",
        {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(request),
        }
    );
    console.log("Delete finished"); 
}

export const registerForSession = async (request: RegisterUserForStudySessionRequest) => {
    const response = await apiFetch(baseSessionUrl + "/register",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(request),
        }
    );

    console.log("User Registered");
}

export const unregisterFromSession = async (request: RegisterUserForStudySessionRequest) => {
    const response = await apiFetch(baseSessionUrl + "/register", 
        {
            method: "Delete",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(request),
        } 
    );

    console.log("User unRegistered");
}

export const getRegisteredSessionsForUser = async (page: number): Promise<SpringBootPagination<StudySession>> => {
    const response = await apiFetch(baseSessionUrl + `/register?page=${page}&size=2`,
        {
            method: "GET",
        }
    )

    const responseBody = await response.json() as SpringBootPagination<ListStudySessionResponse>;

    return {
        ...responseBody,
        content: responseBody.content.map((session) => ({
            id: session.sessionId,
            name: session.name,
            description: session.description,
            session_start: session.sessionStart,
            session_end: session.sessionEnd,
            location: session.location,
            status: session.status,
            organizer: session.organizer,
            attendees: session.attendees,
        })),
    }
}

export const createUser = async (request: CreateUserRequest) => {
    const response = await fetch(baseUserUrl + "/createUser", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    });

    const responseBody = await response.json();

    if (!response.ok) {
        console.error(JSON.stringify(responseBody));
        throw new Error("An unknown error occured");
    }

    console.log(responseBody);
}

// Functions related to user auth only make the http call and return the response to the auth context
// The auth context will handle validation from there
export const login = async (request: UserLoginRequest) => {
    const response = await fetch(baseAuthUrl + "/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    });

    if (!response.ok) {
        throw new Error("Invalid username or password");
    }

    const responseBody = await response.json();

    return responseBody;
}

export const getCurrentUser = async () => {
    const response = await apiFetch(baseAuthUrl + "/getCurrentUser")
    
    return response;
}

export const listStudySessionsForUser = async (page: number): Promise<SpringBootPagination<StudySession>> => {
    const response = await apiFetch(
        baseSessionUrl + `/forUser?page=${page}&size=2`,
        {
            method: "GET",
        }
    );

    const responseBody = await response.json() as SpringBootPagination<ListStudySessionResponse>;

    return {
        ...responseBody,
        content: responseBody.content.map((session) => ({
            id: session.sessionId,
            name: session.name,
            description: session.description,
            session_start: session.sessionStart,
            session_end: session.sessionEnd,
            location: session.location,
            status: session.status,
            organizer: session.organizer,
            attendees: session.attendees,
        })),
    }
};

export const updateUser = async (request: UpdateUserRequest) => {
    const response = await apiFetch(baseUserUrl + "/updateUser", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    });

    const responseBody = await response.json();

    console.log(responseBody);
}

export const updateUserPassword = async (request: UpdateUserPasswordRequest) => {
    const response = await apiFetch(baseUserUrl + "/updatePassword", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    });

    const responseBody = await response.json();

    console.log(responseBody);
}

