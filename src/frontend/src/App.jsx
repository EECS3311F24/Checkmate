import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import { QueryClient, QueryClientProvider, useQuery } from 'react-query'
import HeaderComponent from './components/HeaderComponent'
import ListUserComponent from './components/ListUserComponent'
import UserFormComponent from './components/UserFormComponent'
import UserDataFormComponent from './components/UserDataFormComponent'
import LoginComponent from './components/LoginComponent'
import CheckmateHome from './components/CheckmateHome'
import ChessGame from './components/ChessGameComponent'
import { LanguageProvider } from './components/LanguageProvider'
import ChangePasswordComponent from './components/ChangePasswordComponent'
import { ThemeProvider } from './components/ThemeProvider'

function App() {
  const queryClient = new QueryClient()
  return (
    <>
    <QueryClientProvider client={queryClient}>
    <BrowserRouter>
    <ThemeProvider>
    <LanguageProvider>
      <HeaderComponent/>
      <Routes>
        <Route path='/' element = {<CheckmateHome/>}></Route>
        <Route path='/users' element = {<ListUserComponent/>}></Route>
        <Route path='/signup' element = {<UserFormComponent/>}></Route>
        <Route path='/login' element = {<LoginComponent/>}></Route>
        <Route path='/edit-user/:id' element = {<UserFormComponent/>}></Route>
        <Route path='/change-password/:id' element = {<ChangePasswordComponent/>}></Route>
        <Route path='/account/:id' element = {<UserDataFormComponent/>}></Route>
        <Route path="/play" element={<ChessGame />}></Route>
      </Routes>
      </LanguageProvider>
      </ThemeProvider>
    </BrowserRouter>
    </QueryClientProvider>
    </>
  )
}

export default App