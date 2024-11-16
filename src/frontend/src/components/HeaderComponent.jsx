import React, { useState, useEffect } from 'react'
import { Form, Nav, Navbar } from 'react-bootstrap';
import { getTranslation, useLanguage } from './LanguageProvider';
import { getAuthenticate } from '../services/UserService';

const HeaderComponent = () => {
  const { language, setLanguage } = useLanguage();
  const [user, setUser] = useState();

  useEffect(() => {
    getUser();
  }, [])

  function getUser() {
    getAuthenticate().then(response => {
      setUser(response.data)
    }).catch(e => {})
  }

  return (
    <Navbar className="navbar navbar-dark bg-dark justify-content-between">
      <Navbar.Brand href="/">Checkmate</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
          <Nav.Link href={"/account/" + user?.id} hidden={!user} >{getTranslation("HeaderComponentAccount", language) + ": " + user?.username}</Nav.Link>
          <Nav.Link href="/users">{getTranslation("HeaderComponentUsers", language)}</Nav.Link>
          <Nav.Link href="/signup" hidden={user}>{getTranslation("HeaderComponentSignup", language)}</Nav.Link>
          <Nav.Link href="/login" hidden={user}>{getTranslation("HeaderComponentLogin", language)}</Nav.Link>
          <Nav.Link href="/play">{getTranslation("HeaderComponentPlayChess", language)}</Nav.Link>
        </Nav>
        <Form.Select
          value={language}
          onChange={(e) => setLanguage(e.target.value)}
          className="ms-auto"
          style={{ width: '150px' }}
        >
          <option value="en">English</option>
          <option value="fn">Français</option>
          <option value="es">Español</option>
        </Form.Select>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default HeaderComponent;