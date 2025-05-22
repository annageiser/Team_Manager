import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function TeamOverview() {
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    axios.get('/api/teams').then(res => setTeams(res.data));
  }, []);

  return (
    <div>
      <h2>Teams</h2>
      <ul>
        {teams.map(team => (
          <li key={team.id}>{team.name}</li>
        ))}
      </ul>
    </div>
  );
}