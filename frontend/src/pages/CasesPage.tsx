import { useEffect, useState } from 'react';
import { getCases, deleteCase } from '../api/api';
import type { Case } from '../types/case';

export default function CasesPage() {
  const [cases, setCases] = useState<Case[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    getCases()
      .then(setCases)
      .catch((e: Error) => setError(e.message))
      .finally(() => setLoading(false));
  }, []);

  const handleDelete = async (id: number) => {
    await deleteCase(id);
    setCases(cases.filter(c => c.id !== id));
  };

  if (loading) return <p>Loading cases...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div>
      <h1>Cases</h1>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Status</th>
            <th>Customer ID</th>
            <th>Info</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {cases.map(c => (
            <tr key={c.id}>
              <td>{c.case_name}</td>
              <td>{c.status}</td>
              <td>{c.customerId}</td>
              <td>{c.info}</td>
              <td>
                <button onClick={() => handleDelete(c.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
