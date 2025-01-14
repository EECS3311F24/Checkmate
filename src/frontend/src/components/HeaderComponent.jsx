import React, { useState, useEffect } from 'react';
import { Form, Nav, Navbar } from 'react-bootstrap';
import { getAuthenticate, getUserData, updateUserData } from '../services/UserService';
import { getTranslation, useLanguage } from './LanguageProvider';
import { useTheme } from './ThemeProvider';

const HeaderComponent = () => {
  const { language, setLanguage } = useLanguage();
  const { theme, setTheme } = useTheme();
  const [userData, setUserData] = useState([]);
  const [user, setUser] = useState();
  // Apply different styles for header and nav links based on the current theme
  const headerStyle = theme === 'dark' ? { color: '#ffffff' } : theme === 'solarized' ? { color: '#ffffff' } : { color: '#000000' };

  // Update the background color of the website based on the current theme
  useEffect(() => {
    document.body.style.backgroundColor = theme === 'dark' ? '#121212' : theme === 'solarized' ? '#fdf6e3' : '#ffffff';
    updateData();
  }, [theme]);

  useEffect(() => {
    updateData();
  }, [language]);

  useEffect(() => {
    getUser();
    getData();
  }, [])

  function getUser() {
    getAuthenticate().then(response => {
      setUser(response.data)
    }).catch(e => { })
  }

  function getData() {
    const id = user?.id;
    if (id) {
      getUserData(id).then((response) => {
        setUserData(response.data);
        setLanguage(convertLanguage(response.data.language));
        setTheme(convertTheme(response.data.theme));
      }).catch(error => {})
    }
  }

  function updateData() {
    const id = user?.id;
    if (id) {
      const data = { language: convertLanguage(language), theme: convertTheme(theme), wins: userData.wins, loses: userData.loses, gamesPlayed: userData.gamesPlayed }
      updateUserData(id, data).then((response) => {
        setLanguage(convertLanguage(response.data.language));
        setTheme(convertTheme(response.data.theme));
      }).catch(error => {})
    }
  }

  function convertLanguage(language) {
    if (language === "ENGLISH") return "en";
    if (language === "FRENCH") return "fn";
    if (language === "SPANISH") return "es";
    if (language === "en") return "ENGLISH";
    if (language === "fn") return "FRENCH";
    if (language === "es") return "SPANISH";
    return language;
  }

  function convertTheme(theme) {
    if (theme === "LIGHT") return "light";
    if (theme === "DARK") return "dark";
    if (theme === "SOLARIZED") return "solarized";
    if (theme === "light") return "LIGHT";
    if (theme === "dark") return "DARK";
    if (theme === "solarized") return "SOLARIZED";
    return theme;
  }

  return (
    <Navbar className="navbar justify-content-between" style={{ backgroundColor: theme === 'dark' ? '#333333' : theme === 'solarized' ? '#20b2aa' : '#d3d3d3' }}>
      <Navbar.Brand href="/" style={headerStyle}>Checkmate</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
          <Nav.Link href={"/account/" + user?.id} style={headerStyle} hidden={!user} >{getTranslation("HeaderComponentAccount", language) + ": " + user?.username}</Nav.Link>
          <Nav.Link href="/users" style={headerStyle} >{getTranslation("HeaderComponentUsers", language)}</Nav.Link>
          <Nav.Link href="/signup" style={headerStyle} hidden={user}>{getTranslation("HeaderComponentSignup", language)}</Nav.Link>
          <Nav.Link href="/login" style={headerStyle} hidden={user}>{getTranslation("HeaderComponentLogin", language)}</Nav.Link>
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