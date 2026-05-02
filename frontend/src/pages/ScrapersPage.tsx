import { useScrapers } from '../hooks/useScrapers';
import { Button } from '../components/ui/button';
import { Card, CardHeader, CardTitle, CardDescription, CardContent, CardFooter } from '../components/ui/card';
import { Badge } from '../components/ui/badge';
import { Play, Trash2, Edit, Plus } from 'lucide-react';
import { toast } from 'sonner';

export default function ScrapersPage() {
  const { data: scrapers, isLoading } = useScrapers();

  if (isLoading) return <div>Loading...</div>;

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Scrapers</h1>
        <Button>
          <Plus className="w-4 h-4 mr-2" />
          New Scraper
        </Button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {scrapers?.map((scraper) => (
          <Card key={scraper.id}>
            <CardHeader className="flex flex-row items-center justify-between space-y-0">
              <CardTitle className="text-lg">{scraper.name}</CardTitle>
              <Badge variant={scraper.isActive ? 'success' : 'secondary'}>
                {scraper.isActive ? 'Active' : 'Inactive'}
              </Badge>
            </CardHeader>
            <CardContent>
              <CardDescription className="mb-4">
                {scraper.description || 'No description'}
              </CardDescription>
              <div className="text-xs text-muted-foreground space-y-1">
                <p>Type: {scraper.type}</p>
                <p className="truncate">URL: {scraper.targetUrl}</p>
              </div>
            </CardContent>
            <CardFooter className="gap-2">
              <Button variant="secondary" size="sm">
                <Play className="w-3 h-3 mr-1" /> Run
              </Button>
              <Button variant="outline" size="sm">
                <Edit className="w-3 h-3 mr-1" /> Edit
              </Button>
              <Button 
                variant="ghost" 
                size="sm"
                onClick={() => toast.info('Delete clicked')}
                className="text-destructive hover:text-destructive"
              >
                <Trash2 className="w-3 h-3" />
              </Button>
            </CardFooter>
          </Card>
        ))}
      </div>

      {(!scrapers || scrapers.length === 0) && (
        <div className="text-center py-12 text-muted-foreground">
          No scrapers yet. Create your first one!
        </div>
      )}
    </div>
  );
}