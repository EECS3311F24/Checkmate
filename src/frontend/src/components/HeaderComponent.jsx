import React from 'react'
import { Col, Row, Button, Form, Nav, Navbar } from 'react-bootstrap';
import { getTranslation, useLanguage } from './LanguageProvider';

const HeaderComponent = () => {
  const { language, setLanguage } = useLanguage();

  return (
    <Navbar className="navbar navbar-dark bg-dark justify-content-between">
      <Navbar.Brand href="/">Checkmate</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
          <Nav.Link href="/users">{getTranslation("HeaderComponentUsers", language)}</Nav.Link>
          <Nav.Link href="/signup">{getTranslation("HeaderComponentSignup", language)}</Nav.Link>
          <Nav.Link href="/login">{getTranslation("HeaderComponentLogin", language)}</Nav.Link>
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