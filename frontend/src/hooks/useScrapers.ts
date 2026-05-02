import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import api from '../api/client';

export interface Scraper {
  id: number;
  name: string;
  description?: string;
  type: 'STATIC' | 'JS_RENDER' | 'API';
  targetUrl: string;
  isActive: boolean;
  createdAt: string;
}

export function useScrapers() {
  return useQuery({
    queryKey: ['scrapers'],
    queryFn: async () => {
      const { data } = await api.get('/scrapers');
      return data as Scraper[];
    },
  });
}

export function useCreateScraper() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: async (scraper: Partial<Scraper>) => {
      const { data } = await api.post('/scrapers', scraper);
      return data;
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['scrapers'] });
    },
  });
}

export function useDeleteScraper() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: async (id: number) => {
      await api.delete(`/scrapers/${id}`);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['scrapers'] });
    },
  });
}