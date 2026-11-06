import { createContext, useContext, useEffect, useState, type ReactNode } from "react";

interface PageContextType {
    activePage: CurrentPage;
    changeActivePage: (request: CurrentPage) => void;
}

enum CurrentPage {
    Home = "Home",
    Account = "Account",
}

interface CurrentPageProps {
    children: ReactNode;
}

const CurrentPageContext = createContext<PageContextType | undefined>(undefined);

export function CurrentPageProvider({children}: CurrentPageProps) {
    const [activePage, setActivePage] = useState(CurrentPage.Home);

    const changeActivePage = (request: CurrentPage) => {
        setActivePage(request);
    }

    return (
            <CurrentPageContext.Provider value = {{activePage, changeActivePage: changeActivePage}}>
                {children}
            </CurrentPageContext.Provider>
        )
}

export function useCurrentPage() {
    const context = useContext(CurrentPageContext);

    if (!context) {
        throw new Error("useCurrentPage must be used within CurrentPageProvider");
    }

    return context;
}