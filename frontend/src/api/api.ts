import type { User } from '../types/user';
import type { Customer } from '../types/customer';
import type { Product } from '../types/product';
import type { Case, CaseStatus } from '../types/case';

const BASE_URL = 'http://localhost:8080';

async function request<T>(path: string, options?: RequestInit): Promise<T> {
  const response = await fetch(`${BASE_URL}${path}`, {
    headers: { 'Content-Type': 'application/json' },
    ...options,
  });
  if (!response.ok) {
    const err = await response.json().catch(() => ({ error: response.statusText }));
    throw new Error(err.error ?? 'Request failed');
  }
  // 204 No Content has no body
  if (response.status === 204) return undefined as T;
  return response.json();
}

// ── Users ────────────────────────────────────────────────────────────────────
export const getUsers = () => request<User[]>('/users');
export const getUserById = (id: number) => request<User>(`/users/${id}`);
export const createUser = (user: Omit<User, 'id'>) =>
  request<User>('/users', { method: 'POST', body: JSON.stringify(user) });
export const updateUser = (id: number, user: Omit<User, 'id'>) =>
  request<User>(`/users/${id}`, { method: 'POST', body: JSON.stringify(user) });
export const deleteUser = (id: number) =>
  request<void>(`/users/${id}`, { method: 'DELETE' });

// ── Customers ────────────────────────────────────────────────────────────────
export const getCustomers = () => request<Customer[]>('/customers');
export const getCustomerById = (id: number) => request<Customer>(`/customers/${id}`);
export const createCustomer = (customer: Omit<Customer, 'id'>) =>
  request<Customer>('/customers', { method: 'POST', body: JSON.stringify(customer) });
export const updateCustomer = (id: number, customer: Omit<Customer, 'id'>) =>
  request<Customer>(`/customers/${id}`, { method: 'POST', body: JSON.stringify(customer) });
export const deleteCustomer = (id: number) =>
  request<void>(`/customers/${id}`, { method: 'DELETE' });

// ── Products ─────────────────────────────────────────────────────────────────
export const getProducts = () => request<Product[]>('/products');
export const getProductByArticleNumber = (articleNumber: number) =>
  request<Product>(`/products/${articleNumber}`);
export const createProduct = (product: Omit<Product, 'id'>) =>
  request<Product>('/products', { method: 'POST', body: JSON.stringify(product) });
export const updateProduct = (id: number, product: Omit<Product, 'id'>) =>
  request<Product>(`/product/${id}`, { method: 'PUT', body: JSON.stringify(product) });
export const deleteProduct = (id: number) =>
  request<void>(`/product/${id}`, { method: 'DELETE' });

// ── Cases ────────────────────────────────────────────────────────────────────
export const getCases = () => request<Case[]>('/cases');
export const getCaseById = (id: number) => request<Case>(`/cases/${id}`);
export const createCase = (data: { case_name: string; info: string; customerId: number }) =>
  request<Case>('/cases', { method: 'POST', body: JSON.stringify(data) });
export const updateCase = (id: number, data: { case_name: string; info: string; status: CaseStatus }) =>
  request<Case>(`/cases/${id}`, { method: 'PUT', body: JSON.stringify(data) });
export const deleteCase = (id: number) =>
  request<void>(`/cases/${id}`, { method: 'DELETE' });
export const updateCaseStatus = (id: number, status: CaseStatus) =>
  request<Case>(`/cases/${id}/status`, { method: 'PATCH', body: JSON.stringify({ status }) });
export const addProductToCase = (caseId: number, articleNumber: number, quantity: number) =>
  request<Case>(`/cases/${caseId}/products`, {
    method: 'POST',
    body: JSON.stringify({ articleNumber, quantity }),
  });
export const getUsersForCase = (caseId: number) =>
  request<User[]>(`/cases/${caseId}/users`);
export const assignUserToCase = (caseId: number, userId: number) =>
  request<void>(`/cases/${caseId}/users`, { method: 'POST', body: JSON.stringify({ userId }) });
export const removeUserFromCase = (caseId: number, userId: number) =>
  request<void>(`/cases/${caseId}/users/${userId}`, { method: 'DELETE' });
