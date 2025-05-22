import { gapi } from 'gapi-script';

const CLIENT_ID = 'DEINE_CLIENT_ID.apps.googleusercontent.com';
const API_KEY = 'DEIN_API_KEY';
const SCOPES = 'https://www.googleapis.com/auth/calendar.events';

export function initGoogleClient() {
  gapi.load('client:auth2', () => {
    gapi.client.init({
      apiKey: API_KEY,
      clientId: CLIENT_ID,
      discoveryDocs: ['https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest'],
      scope: SCOPES,
    });
  });
}

export async function signIn() {
  await gapi.auth2.getAuthInstance().signIn();
}

export async function createGoogleCalendarEvent(event) {
  await gapi.client.calendar.events.insert({
    calendarId: 'primary',
    resource: event,
  });
}