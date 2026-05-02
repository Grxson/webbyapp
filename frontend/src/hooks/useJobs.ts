import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import api from '../api/client';

export interface Job {
  id: number;
  scraperId: number;
  status: 'PENDING' | 'RUNNING' | 'COMPLETED' | 'FAILED' | 'CANCELLED';
  startedAt?: string;
  completedAt?: string;
  itemsScraped?: number;
  errorMessage?: string;
  createdAt: string;
}

export function useJobs(page = 0, size = 20) {
  return useQuery({
    queryKey: ['jobs', page, size],
    queryFn: async () => {
      const { data } = await api.get(`/jobs?page=${page}&size=${size}`);
      return data as Job[];
    },
  });
}

export function useRunJob() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: async (scraperId: number) => {
      const { data } = await api.post('/jobs', { scraperId });
      return data;
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['jobs'] });
    },
  });
}

export function useCancelJob() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: async (jobId: number) => {
      await api.post(`/jobs/${jobId}/cancel`);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['jobs'] });
    },
  });
}