import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import HeaderComponent from './components/HeaderComponent'
import ListUserComponent from './components/ListUserComponent'
import UserFormComponent from './components/UserFormComponent'
import CheckmateHome from './components/CheckmateHome'
import { LanguageProvider } from './components/LanguageProvider';

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
        <Route path='/edit-user/:id' element = {<UserFormComponent/>}></Route>
      </Routes>
      </LanguageProvider>
    </BrowserRouter>
    </>
  )
}

export default App