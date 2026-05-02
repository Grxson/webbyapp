import { useQuery } from '@tanstack/react-query';
import api from '../api/client';
import { Button } from '../components/ui/button';
import { Badge } from '../components/ui/badge';
import { Card, CardHeader, CardTitle, CardContent } from '../components/ui/card';
import { Plus, Trash2, RefreshCw } from 'lucide-react';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '../components/ui/table';

export default function ProxiesPage() {
  const { data: proxies } = useQuery({
    queryKey: ['proxies'],
    queryFn: async () => {
      const { data } = await api.get('/proxies');
      return data;
    },
  });

  const getStatusBadge = (status: string) => {
    switch (status) {
      case 'ACTIVE': return <Badge variant="success">{status}</Badge>;
      case 'TESTING': return <Badge variant="warning">{status}</Badge>;
      case 'FAILED': return <Badge variant="destructive">{status}</Badge>;
      default: return <Badge variant="secondary">{status}</Badge>;
    }
  };

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Proxy Pool</h1>
        <div className="flex gap-2">
          <Button variant="secondary">
            <RefreshCw className="w-4 h-4 mr-2" /> Validate All
          </Button>
          <Button>
            <Plus className="w-4 h-4 mr-2" /> Add Proxy
          </Button>
        </div>
      </div>

      <div className="border rounded-lg">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Host</TableHead>
              <TableHead>Port</TableHead>
              <TableHead>Status</TableHead>
              <TableHead>Success</TableHead>
              <TableHead>Fail</TableHead>
              <TableHead>Actions</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {proxies?.map((proxy: any) => (
              <TableRow key={proxy.id}>
                <TableCell className="font-medium">{proxy.host}</TableCell>
                <TableCell>{proxy.port}</TableCell>
                <TableCell>{getStatusBadge(proxy.status)}</TableCell>
                <TableCell>{proxy.successCount}</TableCell>
                <TableCell>{proxy.failCount}</TableCell>
                <TableCell>
                  <Button variant="ghost" size="icon" className="text-destructive hover:text-destructive">
                    <Trash2 className="w-4 h-4" />
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>

      {(!proxies || proxies.length === 0) && (
        <Card>
          <CardContent className="py-12 text-center text-muted-foreground">
            No proxies configured
          </CardContent>
        </Card>
      )}
    </div>
  );
}