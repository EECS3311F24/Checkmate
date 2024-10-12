import React from 'react'
import { Col, Row, Button, Form, Nav, Navbar } from 'react-bootstrap';

const HeaderComponent = () => {
    return (
        <Navbar className="navbar navbar-dark bg-dark justify-content-between">
            <Navbar.Brand href="/">Checkmate</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Nav.Link href="users">Users</Nav.Link>
                    <Nav.Link href="signup">Signup</Nav.Link>
                </Nav>
                <Form inline>
                    <Row>
                        <Col xs="auto">
                            <Form.Control
                                type="text"
                                placeholder="Search User"
                                className=" mr-sm-2"
                            />
                        </Col>
                        <Col xs="auto">
                            <Button type="user">Delete All Users</Button>
                        </Col>
                    </Row>
                </Form>
            </Navbar.Collapse>
        </Navbar>
    )
}

export default HeaderComponent