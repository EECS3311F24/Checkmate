import React, { useEffect } from 'react';
import { Col, Row, Button, Form, Nav, Navbar } from 'react-bootstrap';
import { getTranslation, useLanguage } from './LanguageProvider';
import { useTheme } from './ThemeProvider';

const HeaderComponent = () => {
  const { language, setLanguage } = useLanguage();
  const { theme, setTheme } = useTheme();

  // Apply different styles for header and nav links based on the current theme
  const headerStyle = theme === 'dark' ? { color: '#ffffff' } : theme === 'solarized' ? { color: '#ffffff' } : { color: '#000000' };

  // Update the background color of the website based on the current theme
  useEffect(() => {
    document.body.style.backgroundColor = theme === 'dark' ? '#121212' : theme === 'solarized' ? '#fdf6e3' : '#ffffff';
  }, [theme]);

  return (
    <Navbar className="navbar justify-content-between" style={{ backgroundColor: theme === 'dark' ? '#333333' : theme === 'solarized' ? '#20b2aa' : '#d3d3d3' }}>
      <Navbar.Brand href="/" style={headerStyle}>Checkmate</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
          <Nav.Link href="/users" style={headerStyle}>{getTranslation("HeaderComponentUsers", language)}</Nav.Link>
          <Nav.Link href="/signup" style={headerStyle}>{getTranslation("HeaderComponentSignup", language)}</Nav.Link>
          <Nav.Link href="/play" style={headerStyle}>{getTranslation("HeaderComponentPlayChess", language)}</Nav.Link>
        </Nav>
        <div className="d-flex ms-auto" style={{ gap: '10px' }}>
          <Form.Select
            value={language}
            onChange={(e) => setLanguage(e.target.value)}
            style={{ width: '150px' }}
          >
            <option value="en">English</option>
            <option value="fn">Français</option>
            <option value="es">Español</option>
          </Form.Select>
          <Form.Select
            value={theme}
            onChange={(e) => setTheme(e.target.value)}
            style={{ width: '150px' }}
          >
            <option value="light">Light</option>
            <option value="dark">Dark</option>
            <option value="solarized">Solarized</option>
          </Form.Select>
        </div>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default HeaderComponent;
