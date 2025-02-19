import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  Alert,
  Button,
  Col,
  Container,
  Form,
  FormControl,
  Nav,
  Navbar,
  NavDropdown,
  Row,
} from "react-bootstrap";

function Sample() {
  const [show, setShow] = useState(false);

  return (
    <>
      <Navbar expand='md' bg='dark' variant='dark' fixed='top'>
        <Container>
          <Navbar.Brand href='#home'>Navbar</Navbar.Brand>
          <Navbar.Toggle aria-controls='basic-navbar-nav' />
          <Navbar.Collapse id='basic-navbar-nav'>
            <Nav className='me-auto'>
              <Nav.Link href='#home' active>
                Home
              </Nav.Link>
              <Nav.Link href='#features'>Link</Nav.Link>
              <Nav.Link href='#pricing' disabled>
                Disabled
              </Nav.Link>
              <NavDropdown title='Dropdown' id='basic-nav-dropdown'>
                <NavDropdown.Item href='#action'>Action</NavDropdown.Item>
                <NavDropdown.Item href='#another'>
                  Another action
                </NavDropdown.Item>
                <NavDropdown.Item href='#something'>Something</NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href='#separated'>
                  Separated link
                </NavDropdown.Item>
              </NavDropdown>
            </Nav>
            <Form className='d-flex'>
              <FormControl type='text' placeholder='Search' className='me-2' />
              <Button variant='outline-success'>Search</Button>
            </Form>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <main role='main' className='pt-5'>
        {/* Jumbotron 대체 */}
        <div className='p-5 mb-4 bg-light rounded-3'>
          <Container>
            <h1 className='display-3'>Hello, world!</h1>
            <p>
              This is a template for a simple marketing or informational
              website. It includes a large callout and three supporting pieces
              of content.
            </p>
            <p>
              <Button variant='primary' size='lg'>
                Learn more &raquo;
              </Button>
            </p>
          </Container>
        </div>

        <Container>
          <Row>
            <Col md={4} className='pb-3'>
              <h2>Heading</h2>
              <p>
                Donec id elit non mi porta gravida at eget metus. Fusce dapibus,
                tellus ac cursus commodo, tortor mauris condimentum nibh.
              </p>
              <Button variant='secondary'>View details &raquo;</Button>
            </Col>
            <Col md={4} className='pb-3'>
              <h2>Heading</h2>
              <p>
                Donec id elit non mi porta gravida at eget metus. Fusce dapibus,
                tellus ac cursus commodo, tortor mauris condimentum nibh.
              </p>
              <Button variant='secondary'>View details &raquo;</Button>
            </Col>
            <Col md={4} className='pb-3'>
              <h2>Heading</h2>
              <p>
                Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
                egestas eget quam. Vestibulum id ligula porta felis euismod
                semper.
              </p>
              <Button variant='secondary'>View details &raquo;</Button>
            </Col>
          </Row>
        </Container>

        <hr />
      </main>

      <Alert show={show} variant='success'>
        <Alert.Heading>How's it going?!</Alert.Heading>
        <p>
          Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget
          lacinia odio sem nec elit. Cras mattis consectetur purus sit amet
          fermentum.
        </p>
        <hr />
        <div className='d-flex justify-content-end'>
          <Button onClick={() => setShow(false)} variant='outline-success'>
            Close me ya'll!
          </Button>
        </div>
      </Alert>

      <Container>
        {!show && <Button onClick={() => setShow(true)}>Show Alert</Button>}
      </Container>

      <hr />

      <footer className='container'>
        <p>&copy; Company 2017-2020</p>
      </footer>
    </>
  );
}

export default Sample;
