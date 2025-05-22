import axios from 'axios';

export const getEventsByTeam = (teamId) =>
  axios.get(`/api/events/team/${teamId}`);

export const createEvent = (event) =>
  axios.post('/api/events', event);

export const deleteEvent = (id) =>
  axios.delete(`/api/events/${id}`);