import React, { useState } from "react";
import {
    MDBModal,
    MDBModalHeader,
    MDBModalBody,
    MDBModalFooter,
    MDBBtn,
} from "mdb-react-ui-kit";

const ChatConnections = ({ showModal, setShowModal, connectionData, handleConnectionSelect }) => {
    const [searchQuery, setSearchQuery] = useState("");

    const filteredConnections = connectionData.filter((connection) =>
        connection.name.toLowerCase().includes(searchQuery.toLowerCase())
    );

    const handleSearch = (e) => {
        setSearchQuery(e.target.value);
    };

    return (
        <MDBModal show={showModal} onHide={() => setShowModal(false)}>
            <MDBModalHeader>Select Connection</MDBModalHeader>
            <MDBModalBody>
                <input type="text" placeholder="Search by name" value={searchQuery} onChange={handleSearch} />
                {filteredConnections.map((connection) => (
                    <div key={connection.id}>
                        <button onClick={() => handleConnectionSelect(connection.id, connection.name)}>{connection.name}</button>
                    </div>
                ))}
            </MDBModalBody>
            <MDBModalFooter>
                <MDBBtn color="secondary" onClick={() => setShowModal(false)}>
                    Cancel
                </MDBBtn>
            </MDBModalFooter>
        </MDBModal>
    );
};

export default ChatConnections;
