import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import HeaderComponent from './components/HeaderComponent'
import ListUserComponent from './components/ListUserComponent'
import UserFormComponent from './components/UserFormComponent'
import CheckmateHome from './components/CheckmateHome'
import 'bootstrap/dist/css/bootstrap.min.css';
function App() {
  return (
    <>
    <BrowserRouter>
      <HeaderComponent/>
      <Routes>
        <Route path='/' element = {<CheckmateHome/>}></Route>
        <Route path='/users' element = {<ListUserComponent/>}></Route>
        <Route path='/signup' element = {<UserFormComponent/>}></Route>
        <Route path='/edit-user/:id' element = {<UserFormComponent/>}></Route>
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App