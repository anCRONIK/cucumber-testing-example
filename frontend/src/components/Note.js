import React, { useState, useEffect } from "react";


const Note = props => {
    const initialNoteState = {
        id: null,
        text: "",
        dateCreated: null,
        edited: false
    };

    const [currentNote, setCurrentNote] = useState(initialNoteState);
    const [message, setMessage] = useState("");
}

export default Note;