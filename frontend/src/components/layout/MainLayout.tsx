import { NavLink, Routes, Route, Navigate } from 'react-router-dom';
import { LayoutDashboard, Globe, Play, Database, Download, Server, Bell, Settings, LogOut } from 'lucide-react';
import { useAuthStore } from '../../stores/authStore';
import { useThemeStore } from '../../stores/themeStore';
import { cn } from '../../lib/utils';
import { Button } from '../ui/button';
import { Card, CardHeader, CardTitle } from '../ui/card';
import DashboardPage from '../../pages/DashboardPage';
import ScrapersPage from '../../pages/ScrapersPage';
import JobsPage from '../../pages/JobsPage';
import DataPage from '../../pages/DataPage';
import ExportPage from '../../pages/ExportPage';
import ProxiesPage from '../../pages/ProxiesPage';
import AlertsPage from '../../pages/AlertsPage';
import SettingsPage from '../../pages/SettingsPage';

const navItems = [
  { to: '/dashboard', icon: LayoutDashboard, label: 'Dashboard' },
  { to: '/scrapers', icon: Globe, label: 'Scrapers' },
  { to: '/jobs', icon: Play, label: 'Jobs' },
  { to: '/data', icon: Database, label: 'Data' },
  { to: '/export', icon: Download, label: 'Export' },
  { to: '/proxies', icon: Server, label: 'Proxies' },
  { to: '/alerts', icon: Bell, label: 'Alerts' },
  { to: '/settings', icon: Settings, label: 'Settings' },
];

export default function MainLayout() {
  const { user, logout } = useAuthStore();
  const { theme } = useThemeStore();

  return (
    <div className={cn('min-h-screen flex', theme === 'dark' && 'dark')}>
      <aside className="w-64 bg-card border-r flex flex-col">
        <CardHeader className="border-b">
          <CardTitle className="text-xl">Scraper Platform</CardTitle>
          <p className="text-sm text-muted-foreground">{user?.username}</p>
        </CardHeader>
        
        <nav className="flex-1 p-2 space-y-1">
          {navItems.map((item) => (
            <NavLink
              key={item.to}
              to={item.to}
              className={({ isActive }) =>
                cn(
                  'flex items-center gap-3 px-3 py-2 rounded-md transition-colors',
                  isActive
                    ? 'bg-primary text-primary-foreground'
                    : 'hover:bg-muted'
                )
              }
            >
              <item.icon className="w-5 h-5" />
              {item.label}
            </NavLink>
          ))}
        </nav>

        <div className="p-2 border-t">
          <Button
            variant="ghost"
            onClick={logout}
            className="w-full justify-start text-destructive hover:text-destructive hover:bg-destructive/10"
          >
            <LogOut className="w-5 h-5 mr-2" />
            Logout
          </Button>
        </div>
      </aside>

      <main className="flex-1 bg-background p-6">
        <Routes>
          <Route path="/dashboard" element={<DashboardPage />} />
          <Route path="/scrapers" element={<ScrapersPage />} />
          <Route path="/jobs" element={<JobsPage />} />
          <Route path="/data" element={<DataPage />} />
          <Route path="/export" element={<ExportPage />} />
          <Route path="/proxies" element={<ProxiesPage />} />
          <Route path="/alerts" element={<AlertsPage />} />
          <Route path="/settings" element={<SettingsPage />} />
          <Route path="*" element={<Navigate to="/dashboard" replace />} />
        </Routes>
      </main>
    </div>
  );
}