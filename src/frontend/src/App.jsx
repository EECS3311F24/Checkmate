import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import HeaderComponent from './components/HeaderComponent';
import ListUserComponent from './components/ListUserComponent';
import UserFormComponent from './components/UserFormComponent';
import UserDataFormComponent from './components/UserDataFormComponent';
import LoginComponent from './components/LoginComponent';
import CheckmateHome from './components/CheckmateHome';
import ChessGame from './components/ChessGameComponent';
import { LanguageProvider } from './components/LanguageProvider';
import ChangePasswordComponent from './components/ChangePasswordComponent';
import { ThemeProvider } from './components/ThemeProvider';
import ChatBox from './components/ChatBox';

function App() {
  const queryClient = new QueryClient();
  return (
    <>
      <QueryClientProvider client={queryClient}>
        <BrowserRouter>
          <ThemeProvider>
            <LanguageProvider>
              <HeaderComponent />
              <Routes>
                <Route path='/' element={<CheckmateHome />} />
                <Route path='/users' element={<ListUserComponent />} />
                <Route path='/signup' element={<UserFormComponent />} />
                <Route path='/login' element={<LoginComponent />} />
                <Route path='/edit-user/:id' element={<UserFormComponent />} />
                <Route path='/change-password/:id' element={<ChangePasswordComponent />} />
                <Route path='/account/:id' element={<UserDataFormComponent />} />
                <Route path='/play' element={<ChessGame />} />
                <Route path='/chat/:boardId' element={<ChatBox />} />
              </Routes>
            </LanguageProvider>
          </ThemeProvider>
        </BrowserRouter>
      </QueryClientProvider>
    </>
  );
}

export default App;
