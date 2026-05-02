import { Button } from '../components/ui/button';
import { Card, CardHeader, CardTitle, CardContent } from '../components/ui/card';
import { Badge } from '../components/ui/badge';
import { Bell, Plus } from 'lucide-react';

export default function AlertsPage() {
  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Alert Rules</h1>
        <Button>
          <Plus className="w-4 h-4 mr-2" /> New Rule
        </Button>
      </div>

      <Card>
        <CardHeader className="flex flex-row items-center gap-4">
          <Bell className="h-8 w-8 text-muted-foreground" />
          <div>
            <CardTitle>No alerts configured</CardTitle>
            <p className="text-sm text-muted-foreground">Create alert rules to get notified when scraper conditions are met</p>
          </div>
        </CardHeader>
      </Card>

      <h2 className="text-lg font-semibold mt-8 mb-4">Recent Notifications</h2>
      <Card>
        <CardContent className="py-8 text-center text-muted-foreground">
          No notifications
        </CardContent>
      </Card>
    </div>
  );
}