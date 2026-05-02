import { useQuery } from '@tanstack/react-query';
import api from '../api/client';
import { Globe, Play, Database, Bell } from 'lucide-react';
import { Card, CardHeader, CardTitle, CardContent } from '../components/ui/card';

interface Stats {
  totalScrapers: number;
  activeJobs: number;
  totalDataItems: number;
  alerts: number;
}

export default function DashboardPage() {
  const { data: stats } = useQuery<Stats>({
    queryKey: ['dashboard', 'stats'],
    queryFn: async () => {
      const { data } = await api.get('/dashboard/stats');
      return data;
    },
  });

  const statCards = [
    { label: 'Total Scrapers', value: stats?.totalScrapers || 0, icon: Globe, color: 'text-blue-500' },
    { label: 'Active Jobs', value: stats?.activeJobs || 0, icon: Play, color: 'text-green-500' },
    { label: 'Data Items', value: stats?.totalDataItems || 0, icon: Database, color: 'text-purple-500' },
    { label: 'Alerts', value: stats?.alerts || 0, icon: Bell, color: 'text-red-500' },
  ];

  return (
    <div>
      <h1 className="text-2xl font-bold mb-6">Dashboard</h1>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
        {statCards.map((stat) => (
          <Card key={stat.label}>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                {stat.label}
              </CardTitle>
              <stat.icon className={`h-4 w-4 ${stat.color}`} />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">{stat.value}</div>
            </CardContent>
          </Card>
        ))}
      </div>

      <Card>
        <CardHeader>
          <CardTitle>Recent Activity</CardTitle>
        </CardHeader>
        <CardContent>
          <p className="text-muted-foreground text-center py-8">No recent activity</p>
        </CardContent>
      </Card>
    </div>
  );
}