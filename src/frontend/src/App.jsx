import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import HeaderComponent from './components/HeaderComponent'
import ListUserComponent from './components/ListUserComponent'
import UserFormComponent from './components/UserFormComponent'
import LoginComponent from './components/LoginComponent'
import CheckmateHome from './components/CheckmateHome'
import ChessGame from './components/ChessGameComponent'
import { LanguageProvider } from './components/LanguageProvider'
import ChangePasswordComponent from './components/ChangePasswordComponent'

function App() {
  return (
    <>
    <BrowserRouter>
    <LanguageProvider>
      <HeaderComponent/>
      <Routes>
        <Route path='/' element = {<CheckmateHome/>}></Route>
        <Route path='/users' element = {<ListUserComponent/>}></Route>
        <Route path='/signup' element = {<UserFormComponent/>}></Route>
        <Route path='/login' element = {<LoginComponent/>}></Route>
        <Route path='/edit-user/:id' element = {<UserFormComponent/>}></Route>
        <Route path='/change-password/:id' element = {<ChangePasswordComponent/>}></Route>
        <Route path="/play" element={<ChessGame />}></Route>
      </Routes>
      </LanguageProvider>
    </BrowserRouter>
    </>
  )
}

export default App