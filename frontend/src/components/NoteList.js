import React, { useState, useEffect } from "react";
import NoteService from "../services/note.service";
import { Link } from "react-router-dom";
import Pagination from "@material-ui/lab/Pagination";

const NotesList = () => {
  const [notes, setNotes] = useState([]);
  const [currentNote, setCurrentNote] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);

  const [page, setPage] = useState(1);
  const [count, setCount] = useState(0);
  const [pageSize, setPageSize] = useState(3);

  const pageSizes = [3, 6, 9];

  const getRequestParams = (page, pageSize) => {
    let params = {};

    if (page) {
      params["page"] = page - 1;
    }

    if (pageSize) {
      params["size"] = pageSize;
    }

    return params;
  };

  const retrieveNotes = () => {
    const params = getRequestParams(page, pageSize);

    NoteService.getAll(params)
      .then((response) => {
        const { notes, totalPages } = response.data;

        setNotes(notes);
        setCount(totalPages);

        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(retrieveNotes, [page, pageSize]);

  const refreshList = () => {
    retrieveNotes();
    setCurrentNote(null);
    setCurrentIndex(-1);
  };

  const setActiveNote = (note, index) => {
    setCurrentNote(note);
    setCurrentIndex(index);
  };

  const handlePageChange = (event, value) => {
    setPage(value);
  };

  const handlePageSizeChange = (event) => {
    setPageSize(event.target.value);
    setPage(1);
  };

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
        </div>
      </div>
      <div className="col-md-6">
        <h4>Notes</h4>

        <div className="mt-3">
          {"Items per Page: "}
          <select onChange={handlePageSizeChange} value={pageSize}>
            {pageSizes.map((size) => (
              <option key={size} value={size}>
                {size}
              </option>
            ))}
          </select>

          <Pagination
            className="my-3"
            count={count}
            page={page}
            siblingCount={1}
            boundaryCount={1}
            variant="outlined"
            shape="rounded"
            onChange={handlePageChange}
          />
        </div>

        <ul className="list-group">
          {notes && notes.map((note, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "")
                }
                onClick={() => setActiveNote(note, index)}
                key={index}
              >
                {note.text}
              </li>
            ))}
        </ul>

      </div>
    </div>
  );
};

export default NotesList;