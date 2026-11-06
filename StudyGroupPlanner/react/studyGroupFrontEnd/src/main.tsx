import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { Theme } from "@radix-ui/themes";
import { AuthProvider } from './hooks/AuthContext.tsx';
import { CurrentPageProvider } from './context/CurrentPageContext.tsx';
import { StudySessionProvider } from './context/StudySessionContext.tsx';
import { StyledEngineProvider } from '@mui/material';
import GlobalStyles from '@mui/material/GlobalStyles';
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

const queryClient = new QueryClient({defaultOptions: {
    queries: {
      staleTime: 1000 * 60 * 5,
      retry: 1,
    },
  },
});


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <StyledEngineProvider enableCssLayer>
        <GlobalStyles styles = "@layer theme, base, mui, components, utilities;" />
          <AuthProvider>
            <CurrentPageProvider>
              <StudySessionProvider>
                <Theme>
                  <App />

                  <ReactQueryDevtools initialIsOpen={false} />
                </Theme>
              </StudySessionProvider>
            </CurrentPageProvider>
          </AuthProvider>
      </StyledEngineProvider>
    </QueryClientProvider>
  </StrictMode>,
)
