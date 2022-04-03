import React, { useState, useEffect } from "react";


const ApiError = props => {
    const initialState = {
        description: "",
        error: "",
        timestamp: null,
        errors: null
    };

    const [currentError, setCurrentError] = useState(initialState);
}

export default ApiError;