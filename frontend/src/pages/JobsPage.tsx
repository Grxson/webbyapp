import { useJobs } from '../hooks/useJobs';
import { Button } from '../components/ui/button';
import { Badge } from '../components/ui/badge';
import { X, RotateCcw } from 'lucide-react';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '../components/ui/table';

export default function JobsPage() {
  const { data: jobs, isLoading } = useJobs();

  if (isLoading) return <div>Loading...</div>;

  const getStatusBadge = (status: string) => {
    switch (status) {
      case 'COMPLETED': return <Badge variant="success">{status}</Badge>;
      case 'RUNNING': return <Badge variant="default">{status}</Badge>;
      case 'FAILED': return <Badge variant="destructive">{status}</Badge>;
      case 'PENDING': return <Badge variant="warning">{status}</Badge>;
      default: return <Badge variant="secondary">{status}</Badge>;
    }
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-6">Jobs</h1>

      <div className="border rounded-lg">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>Status</TableHead>
              <TableHead>Items</TableHead>
              <TableHead>Started</TableHead>
              <TableHead>Actions</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {jobs?.map((job) => (
              <TableRow key={job.id}>
                <TableCell className="font-medium">#{job.id}</TableCell>
                <TableCell>{getStatusBadge(job.status)}</TableCell>
                <TableCell>{job.itemsScraped || '-'}</TableCell>
                <TableCell className="text-muted-foreground">
                  {job.startedAt ? new Date(job.startedAt).toLocaleString() : '-'}
                </TableCell>
                <TableCell>
                  <div className="flex gap-2">
                    {job.status === 'RUNNING' && (
                      <Button variant="ghost" size="icon">
                        <X className="w-4 h-4" />
                      </Button>
                    )}
                    <Button variant="ghost" size="icon">
                      <RotateCcw className="w-4 h-4" />
                    </Button>
                  </div>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>

      {(!jobs || jobs.length === 0) && (
        <div className="text-center py-12 text-muted-foreground">No jobs yet</div>
      )}
    </div>
  );
}