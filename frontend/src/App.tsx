import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import type { User } from './types/user'; 
import './App.css'

function App() {
  

  const [data, setData] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);


useEffect(() => {
  setLoading(true); // Sätt loading till true när fetch börjar
  
  fetch('http://localhost:8080/users')
    .then(response => {
      if(!response.ok){
        throw new Error('Ingen response från servern');
      }
      return response.json();
    })
    .then((data: User[]) => {
      setData(data);
      setLoading(false);
    })
    .catch(error => {
      setError(error.message);
      setLoading(false);
    });
}, []);

  if(loading) return <div>vänta!!!!....!!!!</div>
  if(error) return <div>error: {error}</div>

  return (
    <>
      <div>
        <h1>Users</h1>
        <ul>
        {data.map(user => (
            <li key={user.id}>
              {user.first_name} - 
              {user.last_name} - 
              {user.phone_number} - 
              {user.email}
            </li>
        ))}
        </ul>
      </div>



    </>
  )
}

export default App
