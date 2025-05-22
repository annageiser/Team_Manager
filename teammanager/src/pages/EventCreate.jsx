import React, { useEffect, useState } from 'react';
import { initGoogleClient, signIn, createGoogleCalendarEvent } from '../api/googleCalendarApi';
import { createEvent } from '../api/eventApi';

export default function EventCreate() {
  const [summary, setSummary] = useState('');
  const [description, setDescription] = useState('');
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');

  useEffect(() => {
    initGoogleClient();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Backend-Event anlegen
    await createEvent({ summary, description, dateTime: `${date}T${time}:00` });
    // Google Kalender Event anlegen
    await signIn();
    await createGoogleCalendarEvent({
      summary,
      description,
      start: { dateTime: `${date}T${time}:00`, timeZone: 'Europe/Berlin' },
      end: { dateTime: `${date}T${parseInt(time.split(':')[0]) + 2}:00`, timeZone: 'Europe/Berlin' },
    });
    alert('Event erstellt!');
  };

  return (
    <form onSubmit={handleSubmit}>
      <input value={summary} onChange={e => setSummary(e.target.value)} placeholder="Titel" required />
      <input value={description} onChange={e => setDescription(e.target.value)} placeholder="Beschreibung" />
      <input type="date" value={date} onChange={e => setDate(e.target.value)} required />
      <input type="time" value={time} onChange={e => setTime(e.target.value)} required />
      <button type="submit">Event erstellen & in Google Kalender eintragen</button>
    </form>
  );
}