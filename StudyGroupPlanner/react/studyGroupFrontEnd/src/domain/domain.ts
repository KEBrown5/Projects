// This lists the data types and their required fields

// Base study session object
export interface StudySession {
  id: string;
  name: string;
  description: string;
  session_start: Date;
  session_end: Date;
  location: string;
  status: SessionStatusEnum;
  created_at?: Date;
  updated_at?: Date;
  organizer: UserSummaryDto;
  attendees: UserSummaryDto[];
}

export enum SessionStatusEnum {
    UPCOMING = "UPCOMING",
    IN_PROGRESS = "IN_PROGRESS",
    ENDED = "ENDED",
}

export interface CreateStudySessionRequest {
    name: string;
    description: string;
    session_start: Date | null;
    session_end: Date | null;
    location: string;
    status: SessionStatusEnum;
}

export interface CreateStudySessionResponse {
    id: string;
    name: string;
    description: string;
    session_start: Date;
    session_end: Date;
    location: string;
    status: SessionStatusEnum;
    created_at: Date;
    updated_at: Date;
}

export interface UpdateStudySessionRequest {
    sessionId: string;
    name: string;
    description: string;
    session_start: Date;
    session_end: Date;
    location: string;
    status: SessionStatusEnum;
}

export interface DeleteStudySessionRequest {
    id: string;
}

export interface RegisterUserForStudySessionRequest {
    sessionId: string;
}

export interface ListStudySessionResponse {
    sessionId: string;
    name: string;
    description: string;
    sessionStart: Date;
    sessionEnd: Date;
    location: string;
    status: SessionStatusEnum;
    organizer: UserSummaryDto;
    attendees: UserSummaryDto[];
}

export interface UserSummaryDto {
    userId: String;
    name: String;
    email: String;
}

export interface SpringBootPagination<T> {
    content: T[]; // The actual data items for the current page
    pageable: {
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    };
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
  };
  last: boolean; // Whether this is the last page
  totalElements: number; // Total number of items across all pages
  totalPages: number; // Total number of pages
  size: number; // Page size (items per page)
  number: number; // Current page number (zero-based)
  sort: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  };
  first: boolean; // Whether this is the first page
  numberOfElements: number; // Number of items in the current page
  empty: boolean; // Whether the current page has no items
}

//
// User objects

export interface User {
    id: string;
    name: string;
    email: string;
    password: string;
}

export interface UserLoginRequest {
    email: string;
    password: string;
}

export interface CreateUserRequest {
    name: string;
    email: string;
    password: string;
}

export interface UpdateUserRequest {
    id: string;
    name: string;
    email: string;
}

export interface UpdateUserPasswordRequest {
    id: string;
    password: string;
    confirmPassword: string;
}





