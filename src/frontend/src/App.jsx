import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import HeaderComponent from './components/HeaderComponent'
import ListUserComponent from './components/ListUserComponent'
import UserFormComponent from './components/UserFormComponent'

function App() {
  return (
    <>
    <BrowserRouter>
      <HeaderComponent/>
      <Routes>
        <Route path='/users' element = {<ListUserComponent/>}></Route>
        <Route path='/signup' element = {<UserFormComponent/>}></Route>
        <Route path='/edit-user/:id' element = {<UserFormComponent/>}></Route>
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App