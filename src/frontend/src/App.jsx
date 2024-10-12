import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import HeaderComponent from './components/HeaderComponent'
import ListUserComponent from './components/ListUserComponent'
import SignupComponent from './components/SignupComponent'

function App() {
  return (
    <>
    <BrowserRouter>
      <HeaderComponent/>
      <Routes>
        <Route path='/users' element = {<ListUserComponent/>}></Route>
        <Route path='/signup' element = {<SignupComponent/>}></Route>
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App