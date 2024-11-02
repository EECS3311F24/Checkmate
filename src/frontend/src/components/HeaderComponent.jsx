import React from 'react';
import { Col, Row, Button, Form, Nav, Navbar } from 'react-bootstrap';
import { getTranslation } from './LanguageProvider';

const HeaderComponent = () => {
    return (
        <Navbar className="navbar navbar-dark bg-dark justify-content-between">
            <Navbar.Brand href="/">Checkmate</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Nav.Link href="/users">{getTranslation("HeaderComponentUsers")}</Nav.Link>
                    <Nav.Link href="/signup">{getTranslation("HeaderComponentSignup")}</Nav.Link>
                </Nav>
                <Form.Select
                    value={"en"}
                    onChange={(e) => {}}
                    className="ms-auto"
                    style={{ width: '150px' }}
                >
                    <option value="en">English</option>
                    <option value="fr">French</option>
                    <option value="es">Spanish</option>
                </Form.Select>
            </Navbar.Collapse>
        </Navbar>
    );
};

export default HeaderComponent;