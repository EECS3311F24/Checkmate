import React from 'react';
import { Navbar, Nav, Form } from 'react-bootstrap';
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
        </Nav>
        <Form.Select
          value={language}
          onChange={(e) => setLanguage(e.target.value)}
          className="ms-auto"
          style={{ width: '150px' }}
        >
          <option value="en">English</option>
          <option value="fn">French</option>
          <option value="es">Spanish</option>
        </Form.Select>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default HeaderComponent;