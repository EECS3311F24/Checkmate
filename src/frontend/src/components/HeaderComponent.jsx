import React from 'react'
import { Col, Row, Button, Form, Nav, Navbar } from 'react-bootstrap';

const HeaderComponent = () => {
    return (
        <Navbar className="navbar navbar-dark bg-dark justify-content-between">
            <Navbar.Brand href="/">Checkmate</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Nav.Link href="/users">Users</Nav.Link>
                    <Nav.Link href="/signup">Signup</Nav.Link>
                    <Nav.Link href="/play">Play Chess</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    )
}

export default HeaderComponent